package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    if (rootDirectory == null){
      return;
    }

    //I'm not the one who made the spelling mistake
    vistor.visit(rootDirectory);

    File[] children = rootDirectory.listFiles();
    if(children == null) {
      return;
    }

    Arrays.sort(children);
    for (File file : children) {
      //here we recursively use explore if the child is a directory if it's a file we visit it
      if (file.isDirectory()) {
        explore(file, vistor);
      }else{
        vistor.visit(file);
      }
    }
  }
}
