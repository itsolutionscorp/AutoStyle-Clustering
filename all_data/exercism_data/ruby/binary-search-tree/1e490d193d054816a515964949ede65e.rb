#!/usr/bin/env ruby

# Exercism 28
# Binary Search Tree

class Bst

attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(data)
    if data <= self.data
      do_insert("left", data)
    else
      do_insert("right", data)
    end
  end

  def do_insert(side, data)
    if self.send(side)
      self.send(side).insert(data)
    else
      self.send("#{side}=",Bst.new(data))
    end
  end

  def each(&block)
    self.left.each(&block) if self.left
    yield data
    self.right.each(&block) if self.right
  end

end
