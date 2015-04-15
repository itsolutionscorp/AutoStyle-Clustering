require 'stringio'

class Beer
  def sing(start, finish=0)
    @output = StringIO.new

    start.downto(finish) do |bottles|
      verse(bottles)
      @output.puts "\n"
    end

    @output.string
  end

  def verse(bottles)
    @output ||= StringIO.new

    how_many = supply(bottles)
    @output.puts "#{how_many} of beer on the wall, #{how_many.downcase} of beer."

    how_many = supply(take_one_down(bottles))
    @output.puts "#{action(bottles)}, #{how_many.downcase} of beer on the wall."

    @output.string
  end

  private

  def supply(bottles)
    case bottles
    when 0
      "No more bottles"
    when 1
      "1 bottle"
    else
      "#{bottles} bottles"
    end
  end

  def action(bottles)
    case bottles
    when 0
      "Go to the store and buy some more"
    when 1
      "Take it down and pass it around"
    else
      "Take one down and pass it around"
    end
  end

  def take_one_down(bottles)
    (bottles - 1) % 100
  end
end
