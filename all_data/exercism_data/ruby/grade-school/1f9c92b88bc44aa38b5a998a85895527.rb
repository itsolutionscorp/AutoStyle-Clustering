class School
  attr_reader :db

  def initialize
    @db = Hash.new { [] }
    def @db.grades; keys; end
  end

  def add(name, grade)
    @db[grade] <<= name
  end

  def grade(level)
    @db[level]
  end

  def sort
    @db.grades.sort.inject({}) do |sorted, grade|
      sorted[grade] = @db[grade].sort
      sorted
    end
  end
end
