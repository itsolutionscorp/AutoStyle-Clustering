class Hamming
end

public

# i had to define compute *outside* of the class to get the tests to work
# why do i have to do this?

def compute(code, template)
  # first string is assumed to be coding strand
  # second string is assumed to be template strand
  length = get_length(code, template)
  difference = calculate_difference(code, template, length)
end

private

def string_to_array(string)
  string.split("")
end

def get_length(code, template)
  [string_to_array(code).length, string_to_array(template).length].min
end

def calculate_difference(code, template, length)
  difference = 0
  length.times do |i|
    if string_to_array(code)[i] != string_to_array(template)[i]
      difference += 1
    end
  end
  difference
end
