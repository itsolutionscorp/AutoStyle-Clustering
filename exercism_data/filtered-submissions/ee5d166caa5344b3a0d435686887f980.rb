def compute(first, second)
    # Requirements
    raise "Expected a String as the first parameter, but got "  << first.class.to_s  << ": " << first.to_s  unless first.is_a? String
    raise "Expected a String as the second parameter, but got " << second.class.to_s << ": " << second.to_s unless second.is_a? String
    raise "Expected two strings of equal length, but the first one was length " << first.length().to_s << ", while the second one was length " << second.length().to_s unless first.length() == second.length()
    # Business Logic
    differenceCount = 0
    differenceIndexes = "|"       #Debug
    first.split("").each_with_index do |character, index|
      if character != second[index]
        differenceCount += 1
        differenceIndexes << "x"  #Debug
      else
        differenceIndexes << " "  #Debug
      end