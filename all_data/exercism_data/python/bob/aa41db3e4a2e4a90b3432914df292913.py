# -*- coding: utf-8 -*-

# Er, maybe had too much fun with this Python challenge. I don't normally code
# this way in production, but always looking to try something new during
# practice. I've represented people as people factories, capable of passing on
# their genes (behaviors?) to their offspring.

from datetime import date
from sets import Set
import re

class Person:
    def __init__(self, genes):
        self.genes = genes
        self.emotion = 'apathetic'
        self.environment = { 'apathetic' : 'Goo.' }

    def hey(self, message):
        self.listen(message)
        return self.speak()

    def speak(self):
        if self.emotion in self.environment:
            return self.environment[self.emotion]
        else:
            return 'I dont know how to respond to that emotion.'

    def listen(self, message):
        emotions = Set([])
        for key in self.genes:
            result = self.genes[key](message)
            if result:
                emotions.add(key)
        if len(emotions) > 1:
            self.emotion = 'conflicted'
        elif len(emotions) == 1:
            self.emotion = emotions.pop()
        else:
            self.emotion = 'apathetic'

    def experience(self, environment):
        self.environment = dict(self.environment, **environment)

    def reproduce_with(self, other_person):
        offspring_genes = dict(self.genes, **other_person.genes)
        offspring = Person(offspring_genes)
        return offspring

surprised_gene = lambda message: ((message.upper() == message) and (re.search('[A-Za-z]', message)))
angry_gene = lambda message: ((len(message) == 0) or (re.search('^[\W]+$', message)))
agreeable_gene = lambda message: (re.search('\?$', message))

alice = Person({ 'agreeable' : agreeable_gene })
albert = Person({ 'surprised' : surprised_gene, 'angry' : angry_gene })
bob = alice.reproduce_with(albert)
teenage_years = { 'surprised' : 'Woah, chill out!', 'agreeable' : 'Sure.', 'angry' : 'Fine. Be that way!', 'apathetic' : 'Whatever.', 'conflicted' : 'Woah, chill out!' }
bob.experience(teenage_years)

def hey(message):
    return bob.hey(message)
