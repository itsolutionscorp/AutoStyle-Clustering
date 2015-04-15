#!/usr/bin/env ruby
require 'rubygems'

class Matrix

  attr_accessor :rows, :columns
  
  public
  
  def initialize(input)
    @rows = create_rows(input)
    @columns = @rows.transpose
  end

  private 
  
  def create_rows(input)
    input.split("\n").map{ |row| row.split(" ").map(&:to_i) }
  end

end
