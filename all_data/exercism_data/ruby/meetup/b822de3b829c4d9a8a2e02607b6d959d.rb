# methods:
# .<day>teenth          ex: .wednesteenth
# .<occurance>_<day>    ex: .last_monday
class Meetup

  TEENS = [13,14,15,16,17,18,19]
  OCCURANCE_REGEX = /first|second|third|fourth|last_/
  # TODO add REGEX that won't accept *blahteenth* method call

  def initialize(month, year)
    @date = Date.new(year, month)
  end

  def method_missing(meth, *args, &block)
    # 'monteenth => <day>teenth'
    if (meth.to_s =~ /(.+)teenth/)
      day = $1
      find_teenth_for(day)
    # 'first_monday => <occurance>_<day>'
    elsif (meth.to_s =~ OCCURANCE_REGEX)
      args = meth.to_s.split('_') # ['first', 'monday']
      if args[0] == 'last'
        find_last_occurance_of(args[1])
      else
        find_occurance_of_day(*args)
      end
    else
      super
    end
  end

  def respond_to(meth)
    if (meth.to_s =~ /(.+)teenth/)
      true
    elsif (meth.to_s =~ OCCURANCE_REGEX)
      true
    else
      super
    end
  end


  private

  def find_teenth_for(day)
    while( !@date.send("#{day}day?") || !(TEENS.include? @date.day) )
      @date += 1
    end
    @date
  end

  # @day string day of week
  # @occurance int occurance of day in month (0-indexed)
  # @returns Date
  def find_occurance_of_day(occurance, day)
    count = occurance_to_count(occurance)
    match_count = 0
    while ( !@date.send("#{day}?") || match_count != count)
      match_count += 1 if @date.send("#{day}?")
      @date += 1
    end
    @date
  end

  # get last day of month, iterate backwards
  def find_last_occurance_of(day)
    date = @date.next_month - 1
    while ( !date.send("#{day}?") )
      date -= 1
    end
    date
  end

  def occurance_to_count(occurance)
    case occurance
    when 'first'  then 0
    when 'second' then 1
    when 'third'  then 2
    when 'fourth' then 3
    end
  end

end
