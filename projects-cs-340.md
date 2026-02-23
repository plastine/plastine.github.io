---
layout: splash
title: "Projects"
permalink: /projects/cs-340/
---
<!--blue dull html to show it is the old project -->
<h1 style="color:#4a90e2;">Initial Project: CS-340 Project Two</h1>


**Context:** 

*A simple CRUD API for MongoDB using Pymongo*
*A robust DashBoard capable of*: 
*displaying mongoDB json documents, filtering results either based on predetermined specifications or by selecting rows/columns on the table, Displaying database results in a pie chart, Showing the locations (based on longitude and latitude) on a map, with the location highlighted*

<!-- this is the best way I could personally implement folders into github pages. I decided on using buttons as well-->
<a href="https://github.com/plastine/CS-340" class ="btn btn--info" target="_blank" rel="noopener">
  View CS-340 Enhanced Project (GitHub Folder)
</a>

<hr class="neon-divider">
<!--neon pink html to fit the theme for the enhanced project -->
<h1 style="color:#ff2fdc; text-shadow: 0 0 8px rgba(255,47,220,0.8);">
  Enhanced Project: Algorithms and Data Structures
</h1>

**Context:**

This enhancement took the most work, research, and testing to perfect. The idea behind this was to modify the existing dashboard to include some sort of way to filter or process data. However, I decided to change the functionality of the provided interactive leaflet map and introduce a data structure with more interactivity to it. I decided on using a K-dimensional tree, and making it so each selection in MongoDB would correspond to a target node, allowing for the selection of the five nearest rescue animals within the vicinity of that target node. This works with both the database table, as well as a simple click on the leaflet map. The reasoning for a K-D tree has to do with the provided longitude and latitude within the AAC database that was given for the assignment. Longitude and latitude form a two dimensional coordinate, which maps perfectly for a K-D tree. When using the table, all of the various buttons and filtering will remove choices for the tree. When then instead clicking and creating an "anchor point", the tree will partition the map by eliminating large spaces that CANNNOT contain the nearest five neighbors. It then parses through a smaller selection of candidates with coordinates that closely resemble the anchor point, and filters down to the closest five.
 I also fixed the pie chart that displayed breeds, as there were some clipping issues and the graphic looked hideous with all of the breeds selected. 

<!--button for the enhanced project-->
<a href="https://github.com/plastine/plastine.github.io/tree/main/CS_499_refactor_340" class ="btn btn--primary" target="_blank" rel="noopener">
  View CS-340 Enhanced Project (GitHub Folder)
</a>

<hr class="neon-divider">

**Course Outcome Accomplished:** 

*Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution, while managing the trade-offs involved in design choices*

*Demonstrate an ability to use well-founded and innovative techniques, skills, and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry-specific goals*

**Justification:**
I believe this to be both innovative on the initial dashboard within the original class, as well as a good showcase of my skill. It was exceptional difficult to setup a local environment to host the juptyer notebook as well as the outdated sections of juptyer dash, and I had to resolve a lot of conflicts to even setup the original project. I also believe that this follows a really interesting algorithmic principle, and uses it in a way that a K-D tree is almost tailor made for. 

<!--narrative-->
<a href="https://docs.google.com/document/d/1UW5A26D6OAmKiRoRCaV2A23lJONb_EFi1B7S20uTFgs/edit?tab=t.0" class ="btn btn--info" target="_blank" rel="noopener">
  View CS-340 narrative
</a>
