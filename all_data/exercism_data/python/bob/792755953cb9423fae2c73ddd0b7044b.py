#!/usr/bin/env python -u
"""
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
He says 'Fine. Be that way!' if you address him without act. saying anything.
He answers 'Whatever.' to anything else.
"""
import re

class Bob():
    responses = {
                  'whatevs' : 'Whatever.', 
                  'sure'    : 'Sure.', 
                  'chill'   : 'Woah, chill out!',
                  'fine'    : 'Fine. Be that way!',
                  } 
    patterns = { 
                  'whatevs' : '^.*$',
                  'sure'    : '.*\?$', 
                  'chill'   : '^[^a-z]*$',
                  'fine'    : '^[ ]*$',
                  } 


    def hey(self, greeting):
        # hey(): take a greeting, think() about it, and respond()
        return self.respond(self.think(greeting));

    def think(self, greeting):
        # think() takes a greeting and returns an appropriate attitude
        if greeting == None: greeting = ''

        # keys ordered explicitly with deliberation; last match wins
        # given the combination of patterns and expectations:
        #    whatevs < sure < chill < fine
        for key in ['whatevs', 'sure', 'chill', 'fine']:
            if re.search(self.patterns[key], greeting) : attitude = key
        return attitude

    def respond(self, attitude):
        # respond() takes an attitude and returns an appropriate response
        return self.responses[attitude]
