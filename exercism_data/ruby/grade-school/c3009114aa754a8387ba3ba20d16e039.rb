class School

  def initialize
    @students_hash = Hash.new
  end

  def to_hash
    @students_hash.each do |k,v|
      v.sort!
    end
    Hash[@students_hash.sort]
  end

  def add(name, grade)
    if @students_hash[grade].nil?
      @students_hash[grade] = []
    end
    @students_hash[grade] << name
  end

  def grade(grade)
    if @students_hash[grade].nil?
      []
    else
      @students_hash[grade].sort
    end
  end

end
