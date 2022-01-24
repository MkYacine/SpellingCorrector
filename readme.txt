Project introduction:
This project was carried out as part of Programming 2 course, under professer Jian-Yun Nie at Université de Montréal.
This project deals with the spelling correction problem. For the purpose of this project, we consider a very limited correction: Only words stored in a dictionary (which can be read in afile) are considered correct and the grammar is not considered. In a text entered by a user or read from a file, there may be words that are not in the dictionary. These words are "unknown". The role of the Spelling Corrector is (1) to identify these "unknown" words, (2) to suggest 5 words from the dictionary closest to the user to replace him, (3) to replace the word
if a proposal is accepted. After the correction, the user can choose to store the result
in a file.

Most notable skills in this project : Graphical interface, class, interface, structure of
predefined data, and file manipulation.

The course grade given to this work is 100%.
____________________________________________________________________________________________________________________
How to use:
1_ Type text into the designated area or open a file from file->open
2_ Load dictionnary from dictionnary->load
3_ Check spelling from verify->execute
4_ Correct incorrect words by clicking on a highlighted text, choosing a suggestion from the dictionnary, then
cliking the 'Change' button
5_ Save the corrected file from file->save 

Note: If the user text is modified, re-executing a spell check is required before correcting words 
____________________________________________________________________________________________________________________

Class organization:
_Main class
_MVC model classes
_Dictionnaire class: data structure to store the dictionnary, contains 3 methods for loading dictionnary, checking if a word is in the dictionnary, and suggesting 5 corrections for a highlighted text.
_LevenshteinComparator class: used in the dictionnary's suggest method. Implements Comparator<String> interface.
Allows for sorting suggestions by levenshtein distance.

Project done by: Yacine Mkhinini