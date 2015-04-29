class School

  def initialize
    @roster = Hash.new
  end

  def db
    @roster
  end

  def add student, grade
    (@roster[grade] ||= []) << student
  end

  def grade number
    @roster[number] || []
  end

  def sort
    sortedKeys = Hash[@roster.sort]
    Hash[sortedKeys.each_pair{|key,value| sortedKeys[key].sort!}]
  end
end
