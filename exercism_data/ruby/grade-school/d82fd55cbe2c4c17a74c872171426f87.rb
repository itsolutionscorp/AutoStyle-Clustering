class School

  attr_reader :db

  def initialize
    @db = {}
  end

  def add(kid, grade)
    (@db[grade] ||= []) << kid
  end

  def grade(grade)
    @db.fetch(grade) { [] }
  end

  def sort
    sorted_db = {}
    @db.sort.each do |key, value|
      sorted_db[key] = value.sort
    end
    @db = sorted_db
  end

end
