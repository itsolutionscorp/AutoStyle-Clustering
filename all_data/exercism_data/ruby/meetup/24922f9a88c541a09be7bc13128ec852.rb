class Meetup
  attr_accessor :monteenth, :tuesteenth, :wednesteenth, :thursteenth, :friteenth, :saturteenth, :sunteenth
  attr_accessor :first_sunday, :first_monday, :first_tuesday, :first_wednesday, :first_thursday, :first_friday, :first_saturday 
  attr_accessor :second_sunday, :second_monday, :second_tuesday, :second_wednesday, :second_thursday, :second_friday, :second_saturday 
  attr_accessor :third_sunday, :third_monday, :third_tuesday, :third_wednesday, :third_thursday, :third_friday, :third_saturday 
  attr_accessor :fourth_sunday, :fourth_monday, :fourth_tuesday, :fourth_wednesday, :fourth_thursday, :fourth_friday, :fourth_saturday 
  attr_accessor :last_sunday, :last_monday, :last_tuesday, :last_wednesday, :last_thursday, :last_friday, :last_saturday 
  
  def initialize(month, year)
    @@thirteenth_of_month = Date.new(year, month, 13)
    @@first_of_month      = Date.new(year, month, 1)
    @@last_of_month       = Date.new(year, month+1, 1).prev_day if month < 12
    @@last_of_month       = Date.new(year+1, 1, 1).prev_day if month == 12

    find_all_teenths
    find_first_weekday 
    find_second_weekday
    find_third_weekday
    find_fourth_weekday
    find_last_weekday 
  end
  
  def find_all_teenths
    (0..6).each do |i|
      testing_date = @@thirteenth_of_month + i  
      self.sunteenth        = testing_date if testing_date.wday == 0 
      self.monteenth        = testing_date if testing_date.wday == 1 
      self.tuesteenth       = testing_date if testing_date.wday == 2 
      self.wednesteenth     = testing_date if testing_date.wday == 3 
      self.thursteenth      = testing_date if testing_date.wday == 4 
      self.friteenth        = testing_date if testing_date.wday == 5 
      self.saturteenth      = testing_date if testing_date.wday == 6 
     end
  end

  def find_first_weekday
    (0..6).each do |i|
      testing_date = @@first_of_month + i
      self.first_sunday     = testing_date if testing_date.wday == 0 
      self.first_monday     = testing_date if testing_date.wday == 1 
      self.first_tuesday    = testing_date if testing_date.wday == 2 
      self.first_wednesday  = testing_date if testing_date.wday == 3 
      self.first_thursday   = testing_date if testing_date.wday == 4 
      self.first_friday     = testing_date if testing_date.wday == 5 
      self.first_saturday   = testing_date if testing_date.wday == 6 
    end
  end

  def find_second_weekday
    self.second_sunday      = self.first_sunday     + 7         
    self.second_monday      = self.first_monday     + 7
    self.second_tuesday     = self.first_tuesday    + 7   
    self.second_wednesday   = self.first_wednesday  + 7 
    self.second_thursday    = self.first_thursday   + 7  
    self.second_friday      = self.first_friday     + 7    
    self.second_saturday    = self.first_saturday   + 7 
  end
  
  def find_third_weekday
    self.third_sunday      = self.first_sunday      + 14         
    self.third_monday      = self.first_monday      + 14
    self.third_tuesday     = self.first_tuesday     + 14   
    self.third_wednesday   = self.first_wednesday   + 14 
    self.third_thursday    = self.first_thursday    + 14  
    self.third_friday      = self.first_friday      + 14    
    self.third_saturday    = self.first_saturday    + 14 
  end
  
  def find_fourth_weekday
    self.fourth_sunday      = self.first_sunday     + 21         
    self.fourth_monday      = self.first_monday     + 21
    self.fourth_tuesday     = self.first_tuesday    + 21   
    self.fourth_wednesday   = self.first_wednesday  + 21 
    self.fourth_thursday    = self.first_thursday   + 21  
    self.fourth_friday      = self.first_friday     + 21    
    self.fourth_saturday    = self.first_saturday   + 21 
  end

  def find_last_weekday
    (0..6).each do |i|
      testing_date = @@last_of_month -i
      self.last_sunday      = testing_date if testing_date.wday == 0 
      self.last_monday      = testing_date if testing_date.wday == 1 
      self.last_tuesday     = testing_date if testing_date.wday == 2 
      self.last_wednesday   = testing_date if testing_date.wday == 3 
      self.last_thursday    = testing_date if testing_date.wday == 4 
      self.last_friday      = testing_date if testing_date.wday == 5 
      self.last_saturday    = testing_date if testing_date.wday == 6 
    end
  end

end
