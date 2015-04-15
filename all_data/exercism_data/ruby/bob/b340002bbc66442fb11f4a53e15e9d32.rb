class Bob
	def hey(speaker_voice)
		return 'Fine. Be that way!' if is_silent?(speaker_voice)
		return 'Woah, chill out!' if is_shouting?(speaker_voice)
		return 'Sure.' if is_asking?(speaker_voice)
		'Whatever.'
	end

	private

	def is_silent?(speaker_voice)
		speaker_voice.rstrip.empty?
	end

	def is_shouting?(speaker_voice)
		speaker_voice == speaker_voice.upcase
	end

	def is_asking?(speaker_voice)
		speaker_voice.end_with?('?')
	end
end
