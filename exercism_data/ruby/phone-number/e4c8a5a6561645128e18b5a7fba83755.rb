class PhoneNumber
def initialize(input_number)
  @input_number = input_number
end

# refactor using method names to array, call each from the array on the var
def number
  result = normalize(@input_number)
end

def area_code
  number.scan(/./)[0..2].join
end

def to_s
  "(" + area_code + ") " + number.scan(/./)[3..5].join + "-" + number.scan(/./)[6..9].join
end

def normalize(number)
  normalize_methods = [method(:to_all_digits), method(:remove_us_country_code), method(:zeros_if_not_10_digits)]
  normalize_methods.each { |method| number = method.call(number) }
  return number
end

def to_all_digits(number)
  number.scan(/\d/).join('')
end

def remove_us_country_code(number)
  if number.length == 11 && number.scan(/./)[0] == "1"
    number = number.scan(/./)[1..-1].join
  end
  return number
end

def zeros_if_not_10_digits(number)
  number = "0000000000" if number.length != 10
  return number
end

end
