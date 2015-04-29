class School
  attr_reader :db

  def initialize
    @db = Hash.new {|hash, key| hash[key] = []}
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(grade)
    @db[grade]
  end

  def sort
    Hash[@db.sort_values.sort]
  end
end

class Hash
  def sort_values
    merge(self) { |key, value| value.respond_to?(:sort) ? value.sort : value }
  end
end
