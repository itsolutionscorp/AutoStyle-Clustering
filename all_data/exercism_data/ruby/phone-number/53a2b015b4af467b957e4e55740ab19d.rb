class PhoneNumber

  def initialize(value)
    @candidate = value
    normalize_number
    validate
  end

  def number
    @candidate
  end

  def area_code
    @candidate[0..2]
  end
  
  def to_s
    add( @candidate, {0 =>'(' , 4 => ') ', 9 => '-'} )
  end

private

  def add(string, modifications)
    modifications.each { |key, value| string.insert( key, value) }  
    string
  end

  def has_internatial_number?
    @candidate.length == 11 && @candidate.start_with?('1')
  end

  def has_wrong_number_of_digits
    @candidate.length != 10
  end

  def normalize_number
    @candidate = @candidate.gsub(/[\(\).-]|\s/, "")
  end

  def validate
    @candidate = @candidate[1..10] if has_internatial_number?
    @candidate = "0000000000" if has_wrong_number_of_digits
  end
end
