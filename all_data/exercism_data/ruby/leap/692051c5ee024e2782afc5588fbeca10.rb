# year.rb
class Year
  def self.leap?(y)
    ((y % 100).nonzero? || (y % 400).zero?) && (y % 4).zero?
  end
end
