class School
  def initialize
    @school = Hash.new{ |key, value| key[value] = [] }
  end

  def add(name, grade)
    @school[grade].push(name)
  end

  def grade number
    @school[number].sort
  end
  
  def to_hash
    @school.sort.each{|key, value| value.sort!}.to_h
  end
end
