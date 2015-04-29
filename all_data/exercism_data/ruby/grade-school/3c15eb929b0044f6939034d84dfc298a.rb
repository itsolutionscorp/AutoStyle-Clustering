class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, grade)
    @db[grade] ||= []
    @db[grade] << name
  end

  def grade(grade)
    @db[grade] || []
  end

  def sort
    @db.sort
  end
end

class Hash
  def sort
    keys = self.keys.sort
    sorted = keys.map {|k| [k, self[k].sort]}
    Hash[sorted]
  end
end
