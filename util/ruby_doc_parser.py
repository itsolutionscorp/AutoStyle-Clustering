#!/usr/bin/env python
'''
Created on Nov 10, 2014
Comment: This is such a hack -- I have no idea how to parse html,
or if there's a better way to parse the ruby standard library.
@author: joe
'''
from HTMLParser import HTMLParser
from urllib import urlopen
import re
import pickle
import os

class RubyDocPageParser(HTMLParser):
    """
    Class that parses one page of the ruby standard library.
    """
    def __init__(self):
        HTMLParser.__init__(self)
        self.handling = False
        self.key = None
        self.library_dict = {}
    
    def handle_starttag(self, tag, attrs):
        if tag == 'span':
            attrs = dict(attrs)
            if 'class' in attrs:
                if attrs['class'] == 'method-callseq':
                    self.handling = True
            
            
    def handle_endtag(self, tag):
        self.handling = False
    
    def handle_data(self, data):
        if self.handling:
            if not self.key:
                self.key = self.canoncialize(self.strip_to_call(data))
            else:
                if not self.key in self.library_dict:
                    self.library_dict[self.key] = set()
                can_data = self.canoncialize(data)
                if can_data != self.canoncialize(self.url):
                    if self.key == 'each' and can_data == 'hash':
                        pass #TODO: cheating
                    else: 
                        self.library_dict[self.key].add(can_data)
                self.key = None
    
    def canoncialize(self, data):
        data = data.strip()
        match = re.match('(.*?)\ or', data)
        if match:
            data = match.group(1)
        if len(data) > 1 and data[0] == 'a' and data[1].isupper():
            data = data[1:]
        if len(data) > 2 and data[0:2] == 'an' and data[2].isupper():
            data = data[2:]
        data = data.lower()
        if len(data) > 2 and data[0:2] == 'a_':
            data = data[2:]
        if len(data) > 3 and data[0:3] == 'an_':
            data = data[3:]
        if len(data) > 4 and data[0:4] == 'new_':
            data = data[4:]
        data = data.strip()
        if data == 'hsh':
            data = 'hash'
        if data == 'ary':
            data = 'array'
        if data == 'obj':
            data = 'object'
        if data == 'int':
            data = 'integer'
        if data == 'str':
            data = 'string'
        match = re.match('"(.*?)"', data)
        if match:
            data = match.group(1)
        return data
    

    def strip_to_call(self, data):
        match = re.match('(.*?)[{(\ ]', data)
        if match:
            return match.group(1)
        else:
            return ''
        
    def create_lib_call_dict(self, module_file):
        with open(module_file, 'r') as f:
            modules = f.read().splitlines()
        for module in modules:
            print module
            url = urlopen('http://ruby-doc.org/core-2.1.4/' + module + '.html')
            self.url = module
            text = url.read()
            self.feed(text)
        with open('util/lib_call_dict.pkl', 'w') as f:
            pickle.dump(self.library_dict, f)
    
if __name__=='__main__':
    if not os.path.isfile('util/lib_call_dict.pkl'):
        p = RubyDocPageParser()
        p.create_lib_call_dict('util/ruby_modules.txt')
    else:
        with open('util/lib_call_dict.pkl', 'r') as f:
            dictionary = pickle.load(f)
            print dictionary['each']
            print dictionary['new']