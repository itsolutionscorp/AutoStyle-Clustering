class School
  def add(name, grade)
    roster[grade] ||= []

    roster[grade].tap do |students|
      students << name
      students.sort!
    end
  end

  def to_hash
    Hash[roster.sort]
  end

  def grade(grade_number)
    (roster[grade_number] || []).sort
  end

  private
  def roster
    @roster ||= {}
  end
end
