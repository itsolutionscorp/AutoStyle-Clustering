require "deep_clone"

class School
  def initialize
    @db = Hash.new { [] }
  end

  def add name, grade
    @db[grade] <<= name
  end

  def db
    DeepClone.clone @db
  end

  def grade grade
    db[grade] || []
  end

  def sort
    DeepClone.clone Hash[@db.sort.map { |grade, names| [grade, names.sort] }]
  end
end
