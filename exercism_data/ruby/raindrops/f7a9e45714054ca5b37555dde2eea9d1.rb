class Raindrops
  def self.convert(input)
    if input % 3 == 0 && input % 5 == 0 && input % 7 == 0
      'PlingPlangPlong'
    elsif input % 3 == 0 && input % 5 == 0
      'PlingPlang'
    elsif input % 3 == 0 && input % 7 == 0
      'PlingPlong'
    elsif input % 5 == 0 && input % 7 == 0
      'PlangPlong'
    elsif input % 3 == 0
      'Pling'
    elsif input % 5 == 0
      'Plang'
    elsif input % 7 == 0
      'Plong'
    else
      input.to_s
    end
  end
end
