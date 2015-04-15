class School
  def initialize
    @db = empty_school_db
  end

  def add(name, level)
    db[level] << name
  end

  def grade(level)
    db[level].sort
  end

  def to_hash
    all_grades.each_with_object({}) do |level, hash|
      hash[level] = grade(level)
    end
  end

  private

  attr_reader :db

  def empty_school_db
    Hash.new do |db, grade|
      db[grade] = []
    end
  end

  def all_grades
    db.keys.sort
  end
end
