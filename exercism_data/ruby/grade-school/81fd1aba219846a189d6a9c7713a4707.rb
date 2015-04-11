class School

  def initialize(input)
    @school = input
  end

  def add(name, grade)
     @school = [name, grade]
  end

  def to_hash
    roster = {}

    if roster.has_key?(@school[1])
      roster[@school[1]].push(@school[0])
    else
      roster[@school[1]] = []
      roster[@school[1]].push(@school[0])
    end
  end

  def grade(grade)
    roster[:grade][grade]
  end

end

# Need roster
# Roster contains grades and names
# Add names to the appropriate grades

# Student names are contained in an array. Elem1 = Name Elem2 = Grade
# Need to push the name and grade into a hash.
# Need to push Elem1 (String) into the appropriate hash containing Elem2 (FixNum)
# Loop
#   Identify FixNum - Match FixNum to appropriate Grade Hash
#     Push Elem1 into resulting Hash location
# Put the name into the hash where grade indicates
# Find grade place name into hash
