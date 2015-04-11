class School
  attr_reader :db

  def initialize
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(name, year)
    grade(year) << name
  end

  def grade(year)
    db[year]
  end

  def sort
    Hash[years.map { |year| sorted_grade(year) }]
  end

  private

  def years
    db.keys.sort
  end

  def sorted_grade(year)
    [year, grade(year).sort]
  end
end
