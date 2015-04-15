#!/usr/bin/env ruby

# Exercism 18
# School Archiving

class School

  def initialize
    @school = Hash.new
  end

  def add(name, grade)
    @school[grade] == nil ? @school[grade] = [name] : @school[grade] << name
  end

  def to_hash
    @school.values.each { |x| x.sort! }
    @school.count == 1 ? @school : @school.sort.to_h
  end

  def grade(num)
    @school[num] == nil ? [] : @school[num].sort
  end


end
