class Raindrops
  def self.convert(drops)
    @drops = drops
    @drops_size = @drops.to_s.length

    if @drops % 3 == 0 && @drops % 5 == 0 && @drops % 7 == 0
      'PlingPlangPlong'
    elsif @drops % 3 == 0 && @drops % 5 == 0
      'PlingPlang'
    elsif @drops % 3 == 0 && @drops % 7 == 0
      'PlingPlong'
    elsif @drops % 5 == 0 && @drops % 7 == 0
      'PlangPlong'
    elsif @drops % 5 == 0
      'Plang'
    elsif @drops % 7 == 0
      'Plong'
    elsif @drops % 3 == 0
      'Pling'
    else
      @drops.to_s
    end
  end
end
