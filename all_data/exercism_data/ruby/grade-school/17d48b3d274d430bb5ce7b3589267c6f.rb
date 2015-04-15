class School
  def initialize
    @enrollment = Hash.new
  end

  def to_hash
    sorted_enrollment = Hash.new
    @enrollment.keys.sort.each do |grade|
      sorted_enrollment[grade] = grade(grade)
    end
    sorted_enrollment
  end

  def add(name, grade)
    @enrollment[grade].nil? ? @enrollment[grade] = [name] : @enrollment[grade].push(name)
  end

  def grade(grade)
    @enrollment[grade].nil? ? [] : @enrollment[grade].sort
  end
end
