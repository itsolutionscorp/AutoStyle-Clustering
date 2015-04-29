def proverb(objects, qualifier=None):
    result = ''.join(
        'For want of a %s the %s was lost.\n' % (this, other)
        for this, other in zip(objects, objects[1:])
    )

    qualifier_prefix = qualifier + ' ' if qualifier else ''
    result += 'And all for the want of a %s%s.' % (qualifier_prefix, objects[0])

    return result
