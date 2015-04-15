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
    Hash[years.map { |year| [year, grade(year).sort] }]
  end

  private

  def years
    db.keys.sort
  end
end
