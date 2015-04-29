require 'pry'
class OCR
  def initialize(text)
    @text = text
  end

  @keys = {
    0 =>
    [
      [' ', '_', ' '],
      ['|', ' ', '|'],
      ['|', ' ', '|'],
      [' ', '_', ' ']
    ]
  }


  def convert
    ctr = 3
    arr = @text.chars
    binding.pry
    results = []
    while ctr <= @text.chars.length
      results << arr[ctr-3..ctr]
      ctr += 3
    end
    binding.pry
  end

end
