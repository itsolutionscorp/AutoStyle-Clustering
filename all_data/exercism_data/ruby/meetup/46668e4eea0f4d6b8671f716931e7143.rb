class Meetup
  require 'date'
  attr_reader :daynames

  DAY_NUMS = [ 1, 8, 15, 22, 29 ]

  def initialize( month, year )
    @month = month
    @year  = year
    @daynames = Date::DAYNAMES.dup
    rotate_daynames_based_on_first_of_month
    self
  end

  # Extend to Date class??
  def end_of_month( y, m )
    Date.new( y, m, 1 ).next_month.prev_day
    # ( Date.new( y, m , 1 ) - 1 ) >> 1
  end

  def days_in_month( y, m )
    end_of_month( y, m ).day
  end


  private

    def method_missing( meth, *args, &block )
      if meth.to_s =~ /^(first|second|third|fourth|last)_(.+)$/
        ordinal_number_dayname( $1, $2, *args, &block )
      else
        super
      end
    end

    

    def rotate_daynames_based_on_first_of_month
      index = Date.new( @year, @month, 1 ).wday
      @daynames.rotate!( index )
    end

    def ordinal_number_dayname( ordinal_num, dayname, *args, &block )
      if @daynames.include?( dayname.capitalize )
        day_adjust = 0
        ordinal_hash = {  first:  0,
                          second: 1,
                          third:  2,
                          fourth: 3, 
                          last:  -1 }
        every_dayname_in_month = list_all_days( dayname )
        Date.new( @year, @month, every_dayname_in_month[ ordinal_hash[ordinal_num.to_sym ] ] )
      else 
        #error  
      end
    end

    def list_all_days( dayname )
      list = []
      day_adjust = @daynames.index( dayname.capitalize )
      DAY_NUMS.each_index do |i|
        list << ( DAY_NUMS[i] + day_adjust ) unless (DAY_NUMS[i] + day_adjust) > days_in_month( @year, @month )
      end
      list    
    end

    ["sunteenth", "monteenth", "tuesteenth", "wednesteenth", "thursteenth", "friteenth", "saturteenth"].each do |action|
      define_method( action ) { dayteenth( action.capitalize ) }
    end
    
    def dayteenth( day_name_teenth )
      list = list_all_days( day_name_teenth.split( "teenth" ).join + "day" )
      day = 1
      list.each { |d| day = d if (13..19) === d }
      Date.new( @year, @month, day )
    end

    ["sundays", "mondays", "tuesdays", "wednesdays", "thursdays", "fridays", "saturdays"].each do |action|
      define_method( action ) { list_all_days( action[0..-2] ) }
    end
end
