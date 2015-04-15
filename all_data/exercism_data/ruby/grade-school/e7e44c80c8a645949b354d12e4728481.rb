class School

  def initialize
    @school = Hash.new{|hsh,key| hsh[key] = [] }
  end

  def add(name, grade)
    @school[grade] << name
  end

  def to_hash
  	@school.each {|key, value| @school[key] = value.sort}
  	Hash[@school.sort]
  end

  def grade(grade)
  	@school[grade].sort
  end

end
