class School

  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, grade)
    @db[grade].push name
  end

  def grade(n)
    @db.fetch(n, [])
  end

  def sort
    Hash[@db.map { |grade, names| [grade, names.sort] }.sort]
  end

end
