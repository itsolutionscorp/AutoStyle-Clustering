class School

  def initialize
    @school = Hash.new([])
  end

  def to_hash
    Hash[@school.sort]
  end

  def grade(num)
    @school[num]
  end

  def add(name, grade)
    if @school[grade].empty?
      @school.merge!( {grade => [name]} )
    else
      @school[grade].push(name).sort!
    end
  end
end
