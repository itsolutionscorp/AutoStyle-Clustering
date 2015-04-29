class School
  attr_reader :db
  
  def initialize
    @db = Hash.new { [] }
  end

  def add(student, level)
    @db[level] <<= student
  end

  def grade(level)
    dup_grade(level)
  end

  def sort
    sorted = {}
    @db.keys.sort.each { |level| sorted[level] = dup_grade(level).sort }
    sorted
  end

  private
    def dup_grade(level)
      @db[level].map { |student| student.dup }
    end
end
