class Allergies
  def initialize(score)
    @score = score
  end

  def allergic_to?(item_name)
    list.include? item_name
  end

  def list
    @list ||= [].tap do |list|
      while @score > 0
        item = items(@score)

        @score -= item.first
        list << item.last
      end
    end.reverse.uniq
  end

  private

  def items(n)
    {
      128 => 'cats',
      64  =>  'pollen',
      32  => 'chocolate',
      16  => 'tomatoes',
      8   => 'strawberries',
      4   => 'shellfish',
      2   => 'peanuts',
      1   => 'eggs'
    }.find { |value, _| value <= n }
  end
end
