# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 17:57:50 2014

@author: laegrim
"""
import re
import string

def encode(message):
    '''
    Encodes a message into atbash cipher
    '''
    
    #normalize
    message = re.sub('[\W]', '', message)
    message = message.lower()
    
    #make a translation table
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    inverse = 'zyxwvutsrqponmlkjihgfedcba'
    translation_table = string.maketrans(alphabet, inverse)
    
    #encode
    encoded_message = message.translate(translation_table)
    
    #break the encoded message into 5 char peices and return
    return ' '.join([encoded_message[i:i+5] for i in range(0, len(encoded_message), 5)])
    
def decode(code):
    '''
    Decodes a message written in atbash cipher
    '''
    
    #make a translation table
    alphabet = 'abcdefghijklmnopqrstuvwxyz'
    inverse = 'zyxwvutsrqponmlkjihgfedcba'
    translation_table = string.maketrans(inverse, alphabet)
    
    #decode, remove spaces, and return
    return re.sub('[\W]', '', code.translate(translation_table))
