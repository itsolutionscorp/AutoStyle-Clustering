import re

chars ="abcdefghijklmnopqrstuvwxyz"

atbash_table = "".maketrans(chars, chars[::-1]) 

removePuncRE = re.compile("[^a-z0-9]+")
group5RE = re.compile("(.{1,5})")

#Encoding and decoding are almost identical processes
#Encoding also replaces each group of 5 chars by 5 chars and a space
# and then strips away the trailing space if there is one.

def encode(text):
   return group5RE.sub('\\1 ', decode(text)).strip()

def decode(text):
   alphas = removePuncRE.sub("", text.lower() )
   return  alphas.translate(atbash_table)
   
