# returns appropriate PlingPlangPlong string based on input number
class Raindrops
  def self.convert(number)
    output_string = ''

    output_string << 'Pling' if number % 3 == 0
    output_string << 'Plang' if number % 5 == 0
    output_string << 'Plong' if number % 7 == 0

    output_string = number.to_s if output_string.empty?

    output_string
  end
end
