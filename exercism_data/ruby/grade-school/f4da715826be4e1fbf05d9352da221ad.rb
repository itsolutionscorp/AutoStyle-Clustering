class School
  attr_reader :name
  attr_reader :db

  def initialize(name)
    @name = name
    @db   = Hash.new([])
  end

  def add(name, level)
    db[level] += [name]
  end

  def grade(level)
    db[level]
  end

  def sort
    Hash[db.keys.sort.map do |level|
      [level, grade(level).sort]
    end]
  end
end
