class School
  def to_hash
    fix_nil_case

    sorted_school = @school.sort_by do |grade, student|
      grade
    end

    sorted_school = sorted_school.to_h

    sorted_school.each do |grade, student|
      sorted_school[grade] = sorted_school[grade].sort
    end

    sorted_school
  end

  def add(student, grade)
    fix_nil_case
    @school[grade] << student
  end

  def grade(search)
    fix_nil_case
    @school[search].sort
  end

  private

  def fix_nil_case
    @school = Hash.new {|hash,key| hash[key] = [] } if @school == nil
  end
end
