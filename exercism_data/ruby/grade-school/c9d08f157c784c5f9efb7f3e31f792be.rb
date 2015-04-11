class School

  attr_reader :db

  def initialize
    @db = Hash.new { [] }
  end

  def add(name, grade)
    db[grade] = db[grade].push name
  end

  def grade(n)
    db[n]
  end

  def sort
    Hash[db.map { |grade, names| [grade, names.sort] }.sort]
  end

end
