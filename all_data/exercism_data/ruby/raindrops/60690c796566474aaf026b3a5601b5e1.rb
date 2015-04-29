class Raindrops
  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(arg)
    result = SOUNDS.reduce('') do |a, e|
      arg % e[0] == 0 ? a + e[1] : a
    end

    result != '' ? result : arg.to_s
  end
end
