class School
  MINGRADE =  1
  MAXGRADE = 12

  attr_reader :db
  def initialize
    @db = Hash.new {|hash,key| hash[key] = [] }
  end

  def add(student=nil, grade=nil)
    return unless valid?(student, grade)
    db[grade] << student
  end

  def grade(gr=0)
    db[gr]
  end

  def sort
    sorted_db = {}
    MAXGRADE.times do |i|
      students = db[i+1]
      sorted_db[i+1] = students.sort unless students.empty?
    end
    sorted_db
  end

private
  def valid?(student, grade)
    student && grade  && (MINGRADE..MAXGRADE).include?(grade)
  end

end
