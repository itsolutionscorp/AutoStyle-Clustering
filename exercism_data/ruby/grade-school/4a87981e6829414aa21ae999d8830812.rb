class School
  attr_reader :db
  def initialize
    @db = Hash.new
  end

  def add(student, grade)
    (@db[grade] ||= []) << student
  end

  def grade(which)
    @db[which] || []
  end

  def sort
    sorted_content = @db.sort.each { |pair| pair[1].sort! }
    Hash[sorted_content]
  end
end
