class School

  attr_reader :hash

  def initialize
    @hash = Hash.new { |hash, key| hash[ key ] = [] }
  end

  def to_hash
    sort
  end

  def grade class_grade
    hash[ class_grade].sort
  end

  def add name, class_grade
    hash[ class_grade ] << name
  end

  def sort
    hash.sort.map do |key, value| [ key, value.sort ] 
    end.to_h
  end
  
end
