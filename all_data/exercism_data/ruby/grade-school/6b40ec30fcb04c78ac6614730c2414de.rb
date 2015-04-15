class Hash

  def hmap(&block)
    Hash[self.map {|k, v| block.call(k,v) }]
  end

  def hsort
    Hash[self.sort]
  end

end

class School

  def initialize
    @grades = {}
  end

  def to_hash
    @grades.hmap {|key, value| [key, value.sort] }.hsort
  end

  def add(name, grade)
    if @grades[grade].nil?
      @grades[grade] = []
    end

    @grades[grade] << name
  end

  def grade(grade)
    @grades[grade] = [] if @grades[grade].nil?
    @grades[grade].sort
  end

end
