class School
  def initialize
   @school = {} 
  end

  def to_hash
    result = {}
    @school.each_pair do |name, grade|
      result[grade] ||= []
      result[grade].push(name).sort!
    end
    Hash[result.sort]
  end

  def add (name, grade)
    @school[name] = grade
  end

  def grade(num)
    @school.select{|name, grade| grade == num}.map{|name, grade| name }.sort
  end

end
