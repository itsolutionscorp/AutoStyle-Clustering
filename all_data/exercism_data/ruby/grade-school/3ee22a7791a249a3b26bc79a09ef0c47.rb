class School
  def initialize
    @scores = Hash.new([])
  end

  def add(person, grade)
    @scores[grade] += [person]
  end

  def grade(num)
    @scores[num].sort
  end

  def to_hash
    build = {}
    @scores.keys.sort.each do |key|
      build = build.merge(key => @scores[key].sort)
    end
    build
  end
end
