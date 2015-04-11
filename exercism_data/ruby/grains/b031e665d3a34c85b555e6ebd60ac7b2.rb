#I used the ruby-prof Gem to profile my code
#gem install ruby-prof
require 'ruby-prof'

class Grains
  def square(number)
    2**(number-1)
  end
  
  def total
    2**(64)-1
  end
  
  def show
    results = ""
    (1..64).each do |square_number|
      results  << "Square #{square_number}: #{square(square_number)}\n"
    end
    results << "Total: #{total}\n"
    puts results
  end
end

# Start profiling
RubyProf.start

# Calling the show in order to produce the output
Grains.new.show

#End profiling
result = RubyProf.stop

# Print a flat profile to text
printer = RubyProf::FlatPrinter.new(result)
printer.print(STDOUT)
