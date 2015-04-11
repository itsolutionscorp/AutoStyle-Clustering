class Year
  # Added memoizing code as an exercise. 

  def initialize (value)
    @year = value
  end

  def leap_year?
    # Not sure if this logic can be clearer...
    fourth_year? and (not hundredth_year? or four_hundredth_year?)
  end

  def fourth_year?
    @year % 4 == 0
  end

  def hundredth_year?
    @year % 100 == 0
  end

  def four_hundredth_year?
    @year % 400 == 0
  end

  def self.leap?(value)

    # Ensure class cache variable 'results' is initialized
    @@results ||= {}

    # Return cached value if present 
    return @@results[value] unless @@results[value].nil?

    # Calculate, store and return value.
    @@results[value] = Year.new(value).leap_year?

  end

  private :fourth_year?, :hundredth_year?, :four_hundredth_year?
  
end
