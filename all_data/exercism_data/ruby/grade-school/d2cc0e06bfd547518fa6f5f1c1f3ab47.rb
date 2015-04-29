class School
  attr_reader :db

  def initialize
    @db = Hash.new([])
  end

  def add(name, grade)
    db[grade] += [name]
  end

  def grade(number)
    db[number]
  end

  def sort
    Hash[sorted_db]
  end

  private

  def sorted_db
    db.sort.map do |number, names|
      [number, names.sort]
    end
  end
end
